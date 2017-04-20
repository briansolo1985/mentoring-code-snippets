package sftp;

import java.io.File;
import java.io.IOException;

import org.apache.commons.vfs2.AllFileSelector;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.UserAuthenticator;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.auth.StaticUserAuthenticator;
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.local.LocalFile;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;

import com.jcraft.jsch.UserInfo;

public class SftpPoc {
  public static void main(String[] args) throws IOException {
    String remoteURL = "sftp://127.0.0.1/data/ftp";
    String keyPath = "/home/ferenk/.ssh/id_rsa";
    String keyPhrase = "ferenk1234";
    String localURL = "file:///home/ferenk/data/local";

    FileSystemOptions fsOptions = createDefaultOptions(keyPath, keyPhrase);
    UserAuthenticator auth = new StaticUserAuthenticator(null, "ferenk", null);
    DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(fsOptions, auth);

    FileSystemManager fsManager = VFS.getManager();
    FileObject remoteFileObject = fsManager.resolveFile(remoteURL, fsOptions);
    LocalFile localFile = (LocalFile) fsManager.resolveFile(localURL);
    localFile.copyFrom(remoteFileObject, new AllFileSelector());
  }

  private static FileSystemOptions createDefaultOptions(String keyPath, String passphrase) throws FileSystemException {
    FileSystemOptions options = new FileSystemOptions();

    SftpFileSystemConfigBuilder configBuilder = SftpFileSystemConfigBuilder.getInstance();
    configBuilder.setStrictHostKeyChecking(options, "no");
    configBuilder.setUserDirIsRoot(options, true);
    configBuilder.setTimeout(options, 10000);
    configBuilder.setUserInfo(options, new SftpPassphraseUserInfo(passphrase));
    configBuilder.setIdentities(options, new File[] { new File(keyPath) });

    return options;
  }

  public static class SftpPassphraseUserInfo implements UserInfo {

    private final String passphrase;

    public SftpPassphraseUserInfo(String pp) {
      passphrase = pp;
    }

    public String getPassphrase() {
      return passphrase;
    }

    public String getPassword() {
      return null;
    }

    public boolean promptPassphrase(String arg0) {
      return true;
    }

    public boolean promptPassword(String arg0) {
      return false;
    }

    public void showMessage(String message) {
    }

    public boolean promptYesNo(String str) {
      return true;
    }
  }
}
